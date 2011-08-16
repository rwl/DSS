package com.epri.dss.shared.test;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;

import junit.framework.TestCase;

public class CMatrixTest extends TestCase {

	int n = 6;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test matrix instantiation.
	 */
	public void testCMatrixImpl() {
		CMatrix cm = new CMatrixImpl(n);
		assertNotNull(cm);
	}

	/**
	 * Test matrix order.
	 */
	public void testOrder() {
		CMatrix cm = new CMatrixImpl(n);
		assertEquals(n, cm.order());
	}

	/**
	 * Test getting matrix elements.
	 */
	public void testGetElement() {
		CMatrix cm = new CMatrixImpl(n);
		assertEquals(0, cm.getElement(0, 0).getReal());
		assertEquals(0, cm.getElement(0, 0).getImaginary());
	}

	/**
	 * Test setting matrix elements.
	 */
	public void testSetElement() {
		int i, j;
		double real, imag;
		CMatrix cm = new CMatrixImpl(n);

		i = 0; j = 0;
		real = 2; imag = 3;
		cm.setElement(i, j, new Complex(real, imag));
		assertEquals(real, cm.getElement(i, j).getReal());
		assertEquals(imag, cm.getElement(i, j).getImaginary());

		i = 1; j = 2;
		real = 3; imag = 4;
		cm.setElement(i, j, new Complex(real, imag));
		assertEquals(real, cm.getElement(i, j).getReal());
		assertEquals(imag, cm.getElement(i, j).getImaginary());
	}

	public void testSetElemSym() {
		int i, j;
		double real, imag;
		CMatrix cm = new CMatrixImpl(n);

		i = 1; j = 3;
		real = 2; imag = 5;
		cm.setElemSym(i, j, new Complex(real, imag));
		assertEquals(real, cm.getElement(i, j).getReal());
		assertEquals(imag, cm.getElement(i, j).getImaginary());
		assertEquals(real, cm.getElement(j, i).getReal());
		assertEquals(imag, cm.getElement(j, i).getImaginary());
	}

	public void testAddElement() {
		int i, j;
		double real, imag;
		CMatrix cm = new CMatrixImpl(n);

		i = 3; j = 2;
		real = 2; imag = 3;
		cm.addElement(i, j, new Complex(real, imag));
		assertEquals(real, cm.getElement(i, j).getReal());
		assertEquals(imag, cm.getElement(i, j).getImaginary());

		cm.addElement(i, j, new Complex(real, imag));
		assertEquals(real + real, cm.getElement(i, j).getReal());
		assertEquals(imag + imag, cm.getElement(i, j).getImaginary());
	}

	public void testAddElemSym() {
		int i, j;
		double real, imag;
		CMatrix cm = new CMatrixImpl(n);

		i = 4; j = 1;
		real = 1; imag = 3;
		cm.addElemSym(i, j, new Complex(real, imag));
		assertEquals(real, cm.getElement(i, j).getReal());
		assertEquals(imag, cm.getElement(i, j).getImaginary());
		assertEquals(real, cm.getElement(j, i).getReal());
		assertEquals(imag, cm.getElement(j, i).getImaginary());

		cm.addElemSym(i, j, new Complex(real, imag));
		assertEquals(real + real, cm.getElement(i, j).getReal());
		assertEquals(imag + imag, cm.getElement(i, j).getImaginary());
		assertEquals(real + real, cm.getElement(j, i).getReal());
		assertEquals(imag + imag, cm.getElement(j, i).getImaginary());
	}

	public void testClear() {
		int i, j, i1, j1, i2, j2;
		double real, imag;
		CMatrix cm = new CMatrixImpl(n);

		i1 = 0; j1 = 0; i2 = 3; j2 = 5;
		real = 2; imag = 3;
		cm.setElement(i1, j1, new Complex(real, imag));
		cm.setElemSym(i2, j2, new Complex(real, imag));

		cm.clear();
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(0, cm.getElement(i, j).getReal());
				assertEquals(0, cm.getElement(i, j).getImaginary());
			}
	}

	public void testVMult() {
		fail("Not yet implemented");
	}

	public void testVMultAccum() {
		fail("Not yet implemented");
	}

	public void testInvert() {
		fail("Not yet implemented");
	}

	public void testAddFrom() {
		fail("Not yet implemented");
	}

	public void testCopyFrom() {
		fail("Not yet implemented");
	}

	public void testGetErrorCode() {
		fail("Not yet implemented");
	}

	public void testSumBlock() {
		fail("Not yet implemented");
	}

	public void testAsArray() {
		fail("Not yet implemented");
	}

	public void testZeroRow() {
		fail("Not yet implemented");
	}

	public void testZeroCol() {
		fail("Not yet implemented");
	}

	public void testAvgDiagonal() {
		fail("Not yet implemented");
	}

	public void testAvgOffDiagonal() {
		fail("Not yet implemented");
	}

	public void testMultByConst() {
		fail("Not yet implemented");
	}

	public void testKron() {
		fail("Not yet implemented");
	}

}
